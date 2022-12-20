import { defineConfig } from "cypress";

export default defineConfig({
  retries: {"runMode": 2, "openMode": 0},
  video: false,
  screenshotOnRunFailure: true,
  viewportWidth: 1200,
  defaultCommandTimeout: 5000,
  pageLoadTimeout: 100000,
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
});
