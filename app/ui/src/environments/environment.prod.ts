export const environment = {
  production: true,
  APP_API:{
    //@ts-ignore
    URL: window["env"]["APP_API_HOST"],
    //@ts-ignore
    HOST: window["env"]["APP_API_HOST"],
    //@ts-ignore
    PORT: window["env"]["APP_API_PORT"],
    BASIC_AUTH:{
      //@ts-ignore
      USERNAME: window["env"]["APP_API_BASIC_USER"],
      //@ts-ignore
      PASSWORD: window["env"]["APP_API_BASIC_PASS"],
    }
  },
};