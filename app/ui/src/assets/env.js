(function(window) {
    window.env = window.env || {};
    // Environment variables
    window["env"]["APP_API_HOST"] = "${APP_API_HOST}";
    window["env"]["APP_API_PORT"] = "${APP_API_PORT}";
    window["env"]["APP_API_BASIC_USER"] = "${APP_API_BASIC_USER}";
    window["env"]["APP_API_BASIC_PASS"] = "${APP_API_BASIC_PASS}";
  })(this);
