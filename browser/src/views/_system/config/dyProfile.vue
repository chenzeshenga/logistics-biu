<template>
    <div class="login-container">
        <div class="app-container">
            <el-form :model="profile" label-width="170px" label-position="left">
                <el-col :span="12">
                    <el-form-item
                        label="企业名称（中文）"
                        prop="chineseName"
                        style="margin-left: 1%"
                    >
                        <el-input v-model="profile.chineseName"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item
                        label="企业名称（英文）"
                        prop="englishName"
                        style="margin-left: 1%"
                    >
                        <el-input v-model="profile.englishName"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item
                        label="企业地址（中文）"
                        prop="chineseAddr"
                        style="margin-left: 1%"
                    >
                        <el-input v-model="profile.chineseAddr"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item
                        label="企业地址（英文）"
                        prop="englishAddr"
                        style="margin-left: 1%"
                    >
                        <el-input v-model="profile.englishAddr"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item
                        label="企业邮编"
                        prop="zipCode"
                        style="margin-left: 1%"
                    >
                        <el-input v-model="profile.zipCode"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item
                        label="企业联系人姓名（中文）"
                        prop="contactEnglishName"
                        style="margin-left: 1%"
                    >
                        <el-input
                            v-model="profile.contactEnglishName"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item
                        label="企业联系人姓名（英文）"
                        prop="contactChineseName"
                        style="margin-left: 1%"
                    >
                        <el-input
                            v-model="profile.contactChineseName"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item
                        label="企业联系方式"
                        prop="phone"
                        style="margin-left: 1%"
                    >
                        <el-input v-model="profile.phone"></el-input>
                    </el-form-item>
                </el-col>
            </el-form>
            <el-col :offset="22" :span="24">
                <el-button
                    type="primary"
                    @click="saveProfile"
                    style="margin-right: 10%"
                    >更新</el-button
                >
            </el-col>
        </div>
    </div>
</template>

<script>
import request from '@/utils/service';

export default {
  name: 'dyProfile',
  data() {
    return {
      profile: {
        chineseName: '',
        englishName: '',
        chineseAddr: '',
        englishAddr: '',
        zipCode: '',
        contactEnglishName: '',
        contactChineseName: '',
        phone: '',
      },
    };
  },
  created() {
    this.initPersonalProfile();
  },
  inject: ['reload'],
  methods: {
    initPersonalProfile() {
      request({
        url: '/profile/init?userId=dy',
        method: 'get',
      }).then((res) => {
        const profile = res.data.data;
        if (profile == null) {
          console.log(profile);
        } else {
          this.profile = profile;
        }
      });
    },
    saveProfile() {
      request({
        url: '/profile/update?userId=dy',
        method: 'post',
        data: this.profile,
      }).then(() => {
        this.$message.success('更新成功');
        this.reload();
      });
    },
  },
};
</script>
