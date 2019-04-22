<template>
  <el-menu class="navbar" mode="horizontal">
    <hamburger :isActive="sidebar.opened" :toggleClick="toggleSideBar" class="hamburger-container"></hamburger>

    <breadcrumb class="breadcrumb-container"></breadcrumb>

    <div class="right-menu">
      <error-log class="errLog-container right-menu-item"></error-log>
      <div style="display: inline-block;">
        <el-row :gutter="20">
          <el-col :span="9">
            <el-input placeholder="订单号快速搜索" v-model="search" style="display: inline-block;" suffix-icon="el-icon-search"
                      clearable
                      @input="quickSearch"></el-input>
          </el-col>
          <el-col :span="7">
            <span>帐号：</span>
            <el-tag style="margin-right: 20px;">{{name}}</el-tag>
          </el-col>
          <el-col :span="8">
            <span>角色：</span>
            <el-tag style="margin-right: 5px;" type="danger" v-if="roles.length===0">游客（未配置任何角色）</el-tag>
            <el-tag :key="r.val" style="margin-right: 5px;" type="success" v-else v-for="r in roles">{{r.name}}</el-tag>
          </el-col>
        </el-row>
      </div>
      <!--      <div style="display:inline-block;width: 40px;margin-left: 5px">-->
      <!--        <el-badge :value="12">-->
      <!--          <span class="el-icon-bell"></span>-->
      <!--        </el-badge>-->
      <!--      </div>-->
      <el-dropdown class="avatar-container right-menu-item" trigger="click" style="margin-bottom: 4px">
        <div class="avatar-wrapper">
          <img :src="avatar" class="user-avatar">
          <i class="el-icon-caret-bottom"></i>
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/">
            <el-dropdown-item>
              东岳
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item>
            <span @click="handleUpdatePwd" style="display:block;">修改密码</span>
          </el-dropdown-item>
          <el-dropdown-item divided>
            <span @click="logout" style="display:block;">退出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <!--弹出窗口：修改密码-->
    <el-dialog :visible.sync="dialogVisible" title="修改密码" width="20%">
      <el-form :model="temp" :rules="rules" label-position="left" label-width="120px" ref="dataForm">

        <el-form-item label="密码" prop="pwd">
          <el-input type="password" v-model="temp.pwd"></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="pwd2">
          <el-input type="password" v-model="temp.pwd2"></el-input>
        </el-form-item>

      </el-form>
      <div class="dialog-footer" slot="footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button @click="updatePwd" type="primary">确定</el-button>
      </div>
    </el-dialog>
  </el-menu>


</template>

<script>
  import {
    mapGetters,
  } from 'vuex';
  import Breadcrumb from '@/components/Breadcrumb';
  import Hamburger from '@/components/Hamburger';
  import ErrorLog from '@/components/ErrorLog';
  import userApi from '@/api/user';
  import request from '@/utils/service';

  export default {

    data() {
      const validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.temp.pwd2 !== '') {
            this.$refs.dataForm.validateField('pwd2');
          }
          callback();
        }
      };

      const validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.temp.pwd) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        dialogVisible: false,
        temp: {
          pwd: null,
          pwd2: null,
        },
        rules: {
          pwd: [
            {
              validator: validatePass, trigger: 'blur',
            }],
          pwd2: [
            {
              validator: validatePass2, trigger: 'change',
            }],
        },
        search: null,
      };
    },
    components: {
      Breadcrumb,
      Hamburger,
      ErrorLog,
    },
    computed: {
      ...mapGetters([
        'sidebar',
        'name',
        'avatar',
        'roles',
      ]),
    },
    methods: {
      toggleSideBar() {
        this.$store.dispatch('toggleSideBar');
      },
      logout() {
        this.$store.dispatch('LogOut').then(() => {
          location.reload();// In order to re-instantiate the vue-router object to avoid bugs
        });
      },
      handleUpdatePwd() {
        this.dialogVisible = true;
        this.$nextTick(() => this.$refs['dataForm'].clearValidate());
      },
      updatePwd() {
        this.$refs['dataForm'].validate((valid) => {
          if (!valid) return;
          // copy obj
          const tempData = Object.assign({}, this.temp);
          userApi.updatePwd(tempData).then(res => {
            this.dialogVisible = false;
            this.$message.success('更新密码成功');
          });
        });
      },
      quickSearch() {
        this.search = this.search.replace('http://47.105.107.242:9527/#/order-info?ord=', '');
        // this.search = this.search.replace('http://localhost:9527/#/order-info?ord=', '');
        if (this.search.length > 3) {
          request({
            url: '/ord/quickSearch/' + this.search,
            method: 'get',
          }).then(res => {
            if ((res.data).hasOwnProperty('data')) {
              this.$confirm('是否查看该订单？', '提示', confirm).then(() => {
                const ord = res.data.data;
                const type = ord.category;
                const orderNo = ord.orderNo;
                const status = ord.status;
                this.$router.push({
                  path: '/order-list/mgt/type' + type + '/status' + status + '?ordno=' + orderNo,
                });
              }).catch(() => {
                this.$message.info('已取消');
                this.search = null;
              });
            }
          });
        } else {
          console.log(this.search);
        }
      },

    },
  };
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .navbar {
    height: 50px;
    line-height: 50px;
    border-radius: 0px !important;

    .hamburger-container {
      line-height: 58px;
      height: 50px;
      float: left;
      padding: 0 10px;
    }

    .breadcrumb-container {
      float: left;
    }

    .errLog-container {
      display: inline-block;
      vertical-align: top;
    }

    .right-menu {
      float: right;
      height: 100%;

      &:focus {
        outline: none;
      }

      .right-menu-item {
        display: inline-block;
        margin: 0 8px;
      }

      .screenfull {
        height: 20px;
      }

      .international {
        vertical-align: top;
      }

      .theme-switch {
        vertical-align: 15px;
      }

      .avatar-container {
        height: 50px;
        margin-right: 30px;

        .avatar-wrapper {
          cursor: pointer;
          margin-top: 5px;
          position: relative;

          .user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 10px;
          }

          .el-icon-caret-bottom {
            position: absolute;
            right: -20px;
            top: 25px;
            font-size: 12px;
          }
        }
      }
    }
  }
</style>
