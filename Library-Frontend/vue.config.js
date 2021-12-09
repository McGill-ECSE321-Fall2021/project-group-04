module.exports = {
  devServer: {
    port: 8087,
  },
  pwa: {
    name: 'Lib.io',
    appleMobileWebAppCapable: 'yes',
    appleMobileWebAppStatusBarStyle: 'black',
    workboxPluginMode: "GenerateSW",
    manifestPath: "public/manifest.json",
  }
};
