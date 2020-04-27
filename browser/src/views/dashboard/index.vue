<template>
    <div class="dashboard-editor-container">
        <div class=" clearfix" style="height: 100px">
            <div class="pan-item" style="height: 100px;width: 100px">
                <div class="pan-info"></div>
                <img class="pan-thumb" :src="avatar" />
            </div>
            <div class="info-container">
                <!--<span class="display_name">{{nick}}</span>-->
                <div
                    style="font-size: 38px;line-height: 38px;color: #212121;padding-top: 15px;"
                >
                    <span
                        style="font-size:20px;padding-top:20px;display:inline-block;"
                        >帐号：</span
                    >
                    {{ nick }}
                </div>
                <div>
                    <span
                        style="font-size:20px;padding-top:20px;display:inline-block;"
                        >角色：</span
                    >
                    <el-tag
                        style="margin-right: 5px;"
                        type="danger"
                        v-if="roles.length == 0"
                        >游客（未配置任何角色）</el-tag
                    >
                    <el-tag
                        style="margin: 5px;"
                        type="success"
                        v-else
                        v-for="r in roles"
                        :key="r.val"
                        >{{ r.name }}</el-tag
                    >
                </div>
            </div>
        </div>
        <el-col :span="24" style="margin-top: 3%">
            <div>
                <span style="font-weight: bold">【运费结算】</span>
                <ol>
                    <li>
                        运费实行系统预充值，实时更新明细，14天发送一次报表清单。
                    </li>
                    <li>预充值金额未使用可随时提现。</li>
                    <li>
                        对于货到付款的订单，快递公司返还到我司账号需一个月，故顺延一个月。
                    </li>
                </ol>
                <span style="font-weight: bold">【运费结算方式】</span>
                <ul>
                    <li>
                        <b>日币：</b
                        >日本银行直接付款、P卡转日本本土银行、国际微信微信支付、国际付款等方式。
                    </li>
                    <li>
                        <b>人民币：</b
                        >中国银行直接付款、支付宝付款、微信付款等方式。
                    </li>
                    <li>
                        <b>注意：</b
                        >我公司报价默认日币，若使用人民币汇款会产生汇损，汇款前需联系业务确认当天汇率，请悉知。
                    </li>
                </ul>
            </div>
        </el-col>
        <span style="font-weight: bold">【我司系统预充值收款账号】</span>
        <el-table :data="tableData" style="margin-top: 1%">
            <el-table-column prop="col1" label="日元账户"> </el-table-column
            ><el-table-column prop="col2" label="日本本土汇款">
            </el-table-column
            ><el-table-column prop="col3" label="国际汇款"> </el-table-column>
        </el-table>
        <el-table :data="tableData2" style="margin-top: 1%">
            <el-table-column prop="col1" label="人民币账户"> </el-table-column
            ><el-table-column prop="col2" label="中国银行"> </el-table-column
            ><el-table-column prop="col3" label="支付宝"> </el-table-column>
            <el-table-column prop="col4" label="微信"> </el-table-column>
        </el-table>
    </div>
</template>

<script>
import {mapGetters} from 'vuex';
import PanThumb from '@/components/PanThumb';

export default {
  name: 'dashboard',
  components: {PanThumb},
  data() {
    return {
      tableData: [
        {
          col1: '账户持有人',
          col2: '日本東岳商事株式会社',
          // col3: 'ZHANG HE',
        },
        {
          col1: '店番号/口座番号',
          col2: '7261964',
          // col3: '651 /7068647',
        },
        {
          col1: '支店名称',
          col2: '岡山支店',
          // col3: 'OKAYAMA',
        },
        // {
        //   col1: 'SWIFT代码',
        //   col2: 'SMBCJPJTXXX',
        //   col3: 'SMBCJPJTXXX',
        // },
        {
          col1: '收款银行全称',
          col2: '三井住友銀行株式会社',
          // col3: 'SUMITOMO MITSUI BANKING CORPORATI',
        },
        // {
        //   col1: '详细地址',
        //   col2:
        //                 '3-4-26 shinkyobashi ,Naka Ku,OKAYAMA-SHI,OKAYAMA 700-0014 JAPAN',
        //   col3:
        //                 '3-4-26 shinkyobashi ,Naka Ku,OKAYAMA-SHI,OKAYAMA 700-0014 JAPAN',
        // },
      ],
      tableData2: [
        {
          col1: '户名',
          col2: '周园园',
          col3: '周园园',
          col4: '東岳商事（周园园）',
        },
        {
          col1: '账号',
          col2: '6217560800008209461',
          col3: '18052128972',
          col4: 'zhouyy19930814',
        },
        {
          col1: '开户支行',
          col2: '中国银行上海嘉定支行',
        },
      ],
    };
  },
  computed: {
    ...mapGetters(['name', 'nick', 'avatar', 'roles', 'perms']),
  },
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.pan-info {
    position: absolute;
    width: inherit;
    height: inherit;
    border-radius: 50%;
    overflow: hidden;
    box-shadow: inset 0 0 0 5px rgba(0, 0, 0, 0.05);
}
.pan-item {
    float: left;
    z-index: 1;
    width: 150px;
    height: 150px;
    border-radius: 50%;
    display: inline-block;
    position: relative;
    cursor: default;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
}
.pan-thumb {
    width: 100%;
    height: 100%;
    background-size: 100%;
    border-radius: 50%;
    overflow: hidden;
    position: absolute;
    transform-origin: 95% 40%;
    transition: all 0.3s ease-in-out;
}

.emptyGif {
    display: block;
    width: 45%;
    margin: 0 auto;
}

.dashboard-editor-container {
    background-color: #f2f6fc;
    min-height: 100vh;
    padding: 50px 60px 0px;
    .pan-info-roles {
        font-size: 12px;
        font-weight: 700;
        color: #333;
        display: block;
    }
    .info-container {
        position: relative;
        margin-left: 190px;
        height: 80px;
        /*line-height: 80px;*/
        .display_name {
            font-size: 38px;
            line-height: 38px;
            color: #212121;
            position: absolute;
            top: 25px;
        }
    }
}

ol li {
    margin: 1%;
}

ul li {
    margin: 1%;
}
</style>
