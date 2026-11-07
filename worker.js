export default {
  async fetch(request, env, ctx) {
    const url = new URL(request.url);

    // Intercept update version check request for Majician App
    if (url.pathname === "/downloads/version.json" || url.pathname === "/version.json") {
      const versionData = {
        versionCode: 2,
        versionName: "2.0",
        forceUpdate: false,
        message: "A new version of Majician app is available with enhanced Shizuku tweaks and location spoofing features!",
        downloadUrl: "https://t.me/udmodz0"
      };
      return new Response(JSON.stringify(versionData, null, 2), {
        status: 200,
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, HEAD, OPTIONS",
          "Access-Control-Allow-Headers": "*"
        }
      });
    }

    // Default response for other endpoints
    return new Response(JSON.stringify({ status: "Majician API Worker Active", developer: "UDMODZ" }, null, 2), {
      status: 200,
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*"
      }
    });
  }
};
