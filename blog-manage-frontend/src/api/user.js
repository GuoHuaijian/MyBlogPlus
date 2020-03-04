import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get',
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post',
  })
}

export function listUser(currentPage,pageSize){
  return request({
    url: '/user/page/'+currentPage+'/'+pageSize,
    method: 'get',
  })
}

export function addUser(data){
  return request({
    url: '/user/add',
    method: 'post',
    data
  })
}

export function updateUser(data){
  return request({
    url: '/user/update',
    method: 'put',
    data
  })
}

export function deleteUser(id){
  return request({
    url: '/user/delete/'+id,
    method: 'delete',
  })
}

export function detailUser(id){
  return request({
    url: '/user/detail/'+id,
    method: 'get',
  })
}
