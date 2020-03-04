import request from '@/utils/request'

export function listTag() {
  return request({
    url: '/tag/list',
    method: 'get',
  })
}

export function TagList(currentPage,pageSize) {
  return request({
    url: '/tag/page/'+currentPage+'/'+pageSize,
    method: 'get',
  })
}

export function TagDetail(id) {
  return request({
    url: '/tag/detail/'+id,
    method: 'get',
  })
}

export function createTag(data) {
  return request({
    url: '/tag/add',
    method: 'post',
    data
  })
}

export function updateTag(data,id) {
  return request({
    url: '/tag/update/'+id,
    method: 'put',
    data
  })
}

export function deleteTag(id) {
  return request({
    url: '/tag/delete/'+id,
    method: 'delete',
  })
}
