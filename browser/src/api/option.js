/**
 * “选项”相关接口
 */
import request from '@/utils/service';

export default {

  listRoleOptions() {
    return request({
      url: '/option/role',
      method: 'get',
    });
  },

};


