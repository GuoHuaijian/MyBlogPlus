import request from '@/utils/request'

export function upload(params) {
  return request({
    url: 'file/upload', // 假地址 自行替换
    method: 'post',
    params
  })
}
