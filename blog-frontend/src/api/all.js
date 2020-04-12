import request from '../utils/request'

export function getCarousel() {
  return request({
    url: '/frontend/carousel',
    method: 'get',
  })
}

export function getArticle(currentPage,pageSize) {
  return request({
    url: '/frontend/page/'+currentPage+'/'+pageSize,
    method: 'get',
  })
}



export function getTag() {
  return request({
    url: '/frontend/tag',
    method: 'get',
  })
}

export function getUser() {
  return request({
    url: '/frontend/user',
    method: 'get',
  })
}

export function getCategory(){
  return request({
    url: '/frontend/category',
    method: 'get',
  })
}

export function getArticleDetail(id){
  return request({
    url: '/frontend/article/'+id,
    method: 'get',
  })
}

export function getArchive(){
  return request({
    url: '/frontend/archive',
    method: 'get',
  })
}

export function getAbout(){
  return request({
    url: '/frontend/about',
    method: 'get',
  })
}



