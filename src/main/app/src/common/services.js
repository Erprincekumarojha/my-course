import axios from 'axios';

//all constant Data
let  BASE_URL = "http://localhost:8096";

export const baseURL = ()=> BASE_URL;

//ALL API call
export const loginAPI = async (url, body) => await axios.post(url+'/login', body);
export const registerAPI = async (url, body) => await axios.post(url+'/register', body);
export const HomeAPI = async (url, body) => await axios.post(url+'/home', body);
export const dataAPI = async (url, body) => await axios.post(url+'/data', body);
export const customerAPI = async (url, body) => await axios.post(url+'/customer', body);