//Comando para Login individual
Cypress.Commands.add("login", () => {
  cy.visit("/login");
  //las siguientes líneas escriben los campos de email , password y se hace click en el botón del formulario
  cy.get("input[name=email]").type("cesar@email.com")
  cy.get("input[name=password]").type("123")
  cy.get("button[type=submit]").click()
  //espero 1 segundo para verificar que estoy en esa página
  cy.wait(1000)
  cy.url().should("contain", "/kanbans")
});

//comando para entrar en el kanban My first Kanban
Cypress.Commands.add("kanban", () => {
  cy.get('.mat-card').click()
  //verifica que la url actual del navegador sea la que quiero
  cy.url().should("contain", "/kanbans/1")
});

//necesario para el drag and drop
require("@4tw/cypress-drag-drop");

