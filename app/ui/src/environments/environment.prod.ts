export const environment = {
  production: true,
   //@ts-ignore
  APP_API:{
    //@ts-ignore
    URL: window["env"]["APP_API_URL"],
    BASIC_AUTH:{
      //@ts-ignore
      USERNAME: window["env"]["BASIC_USER"],
      //@ts-ignore
      PASSWORD: window["env"]["BASIC_PASS"]
    }
  },
};