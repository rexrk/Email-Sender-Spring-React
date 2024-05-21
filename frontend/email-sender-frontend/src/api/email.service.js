import { apiClient } from "./ApiClient";

//api...s

export async function sendEmail(emailData) {
    const result = (await apiClient.post('/send', emailData)).data
    return result
}