import axios from 'axios'

// 打印环境变量，确认IP正确（重启打包后看控制台）
console.log('当前baseURL:', import.meta.env.VITE_API_BASE)

const request = axios.create({
  // 直接写死热点IP+端口，彻底绕开环境变量
  baseURL: 'http://10.40.25.24/api', // 换成你自己的！
  timeout: 15000 // 超时时间拉长，避免热点慢导致超时
})

// 拦截器
request.interceptors.request.use(config => {
  console.log('请求地址:', config.url)
  return config
})

request.interceptors.response.use(
  res => {
    console.log('响应数据:', res.data)
    return res.data
  },
  err => {
    console.error('请求错误:', err)
    return Promise.reject(err)
  }
)

export default request