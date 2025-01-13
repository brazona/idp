export const environment = {
  production: false,
   //@ts-ignore
  APP_API:{
    //@ts-ignore
    URL: window["env"]["APP_API_URL"],
    BASIC_AUTH:{
      //@ts-ignore
      AUTH_USER: window["env"]["BASIC_USER"],
      //@ts-ignore
      AUTH_PASS:window["env"]["BASIC_PASS"]
    }
  },
};