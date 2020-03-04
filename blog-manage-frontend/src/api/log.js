import request from '@/utils/request'

export function LoginLogList(currentPage,pageSize) {
  return request({
    url: '/loginLog/page/'+currentPage+'/'+pageSize,
    method: 'get',
  })
}

export function OperLogList(currentPage,pageSize) {
  return request({
    url: '/operLog/page/'+currentPage+'/'+pageSize,
    method: 'get',
  })
}

export function OperLogDetail(id) {
  return request({
    url: '/operLog/detail/'+id,
    method: 'get',
  })
}

export function VisitLogList(currentPage,pageSize) {
  return request({
    url: '/visitLog/page/'+currentPage+'/'+pageSize,
    method: 'get',
  })
}



