import request from '@/utils/service';

export default {

  queryChannel() {
    return request({
      url: '/channel/list',
      method: 'get',
    });
  },

};
