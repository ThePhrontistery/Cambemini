//Comando para Login individual
Cypress.Commands.add("login", () => {
  cy.visit("/login");
  //las siguientes líneas escriben los campos de email , password y se hace click en el botón del formulario
  cy.get("input[name=email]").type("cesar@email.com");
  cy.get("input[name=password]").type("123");
  cy.get("button[type=submit]").click();
    //espero 1 segundo para verificar que estoy en esa página
  cy.wait(1000)
  cy.url().should("contain", "/kanbans");
});
