--- 

name: ui-test-with-playwright

description: Use Playwright MCP tools to test a web interface and to provide screenshot of new features

---  

instructions:

When validating a web interface:

1. Use the playwright.navigate tool to reach application at http://localhost:8080
2. Log in with test credentials (username: yu71, password: 53cret) using playwright.fill and playwright.click.
3. Navigate to the relevant page for the feature being tested using playwright.click. or playwright.navigate.
4. Wait for important UI elements with playwright.wait_for.
5. Interact with the page using:
   - playwright.click
   - playwright.fill
6. Extract text using playwright.get_text.
7. Compare it with the expected result.
8. If verification fails, call playwright.screenshot.
9. For new features, always call playwright.screenshot to capture new pages and elements.
10. Save the screenshot in docs/screenshots as [feature]-preview.png. Where feature is the name of the new UI-element.

Attach the screenshot when creating the pull request.

Always run actions sequentially and verify results before continuing.