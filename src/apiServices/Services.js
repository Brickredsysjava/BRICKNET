import axios from 'axios';

const commonUrl = "localhost";
const userToken= localStorage.getItem('token')

export const apiPostMethod = async (url, params) => {
  return new Promise(async (resolve, reject) => {
    await axios
      .post(commonUrl.baseUrl + url, params, {
        headers: {
          'content-type': 'application/json',
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then(function (response) {
        resolve(response);
      })

      .catch(function (error) {
        console.log(error);
        reject(error)
      });
  });
};

export const apiGetMethod = async url => {
  return new Promise(async (resolve, reject) => {
    await axios
      .get(commonUrl.baseUrl + url, {
        headers: {
          'content-type': 'application/json',
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then(function (response) {
        resolve(response);
      })
      .catch(function (error) {
        console.log(error);
        reject(error)
      });
  });
};
