import request from '@/utils/request'

export default {

  queryChannel() {
    return request({
      url: "/channel/list",
      method: 'get'
    })
  }

}
