import axios from "axios";
const baseURL='http://localhost:8080/api/email'

export const apiClient = axios.create({
    baseURL:baseURL
})