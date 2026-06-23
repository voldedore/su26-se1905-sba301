import {httpClient} from "./httpClient.js";

export async function getList(page = 1, size = 10) {
    // axios get
    const resp = await httpClient.get(`/artists`, {
        params: {page, size}
    });

    // return response
    return resp;
}