import request from '@/utils/request'

export function listCategory() {
  return request({
    url: '/category/list',
    method: 'get',
  })
}

export function CategoryList(currentPage,pageSize) {
  return request({
    url: '/category/page/'+currentPage+'/'+pageSize,
    method: 'get',
  })
}

export function CategoryDetail(id) {
  return request({
    url: '/category/detail/'+id,
    method: 'get',
  })
}

export function createCategory(data) {
  return request({
    url: '/category/add',
    method: 'post',
    data
  })
}

export function updateCategory(data,id) {
  return request({
    url: '/category/update/'+id,
    method: 'put',
    data
  })
}

export function deleteCategory(id) {
  return request({
    url: '/category/delete/'+id,
    method: 'delete',
  })
}

