import request from '@/utils/request'

export function articleList(currentPage,pageSize) {
  return request({
    url: '/article/page/'+currentPage+'/'+pageSize,
    method: 'get',
  })
}

export function createArticle(data) {
  return request({
    url: '/article/add',
    method: 'post',
    data
  })
}

export function deleteArticle(id) {
  return request({
    url: '/article/delete/'+id,
    method: 'delete',
  })
}

export function ContentDetail(id) {
  return request({
    url: '/article/detail/'+id,
    method: 'get',
  })
}

export function fetchArticle(id) {
  return request({
    url: '/article/detail2/'+id,
    method: 'get',
  })
}

export function updateArticle(data,id) {
  return request({
    url: '/article/update/'+id,
    method: 'put',
    data
  })
}





export function fetchPv(pv) {
  return request({
    url: '/article/pv',
    method: 'get',
    params: { pv }
  })
}

