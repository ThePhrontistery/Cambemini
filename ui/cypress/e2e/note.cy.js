describe("test relacionados con las notas", () => {
  beforeEach(()=>{
    cy.login();
    cy.kanban();
  });
  it("Verificar que al menos hay una nota en el primer swimlane", () => {
    cy.get(
      '#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card'
    ).should("be.visible");
  }); 

    it("Modificar la primera nota del primer swimlane", () => {
    //clicar en el botón de editar
    cy.get(
      '#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card > .mat-card-header > .space-between > :nth-child(2) > :nth-child(2) > .mat-button-wrapper > .mat-icon'
    ).click();
    //verificar que se ve el formulario
    cy.get("mat-dialog-container").should("be.visible");
    //limpiar contenido
    cy.get("textarea[type=text]").clear();
    //escribir nuevo contenido
    cy.get("textarea[type=text]").type("Crear test de integración");
    //darle click a guardar
    cy.contains("Guardar").click();
    //verificar que se ha escrito
    cy.contains("Crear test de integración").should("be.visible");
  });

    it("Eliminar la primera nota del swimlane", () => {
    //clicar en el botón de eliminar
    cy.get(
      '#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card > .mat-card-header > .space-between > :nth-child(2) > :nth-child(3) > .mat-button-wrapper > .mat-icon'
    ).click();
    //verificar el formulario de eliminación
    cy.get("mat-dialog-container").should("be.visible");
    //click en botón eliminar
    cy.contains("Sí").click();
  });
  
  it("Eliminar nota, pero no aceptar", () => {
    //click en el botón de eliminar
    cy.get(
      '#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card > .mat-card-header > .space-between > :nth-child(2) > :nth-child(3) > .mat-button-wrapper > .mat-icon'
    ).click();
    //verificar el formulario de eliminación
    cy.get("mat-dialog-container").should("be.visible");
    //click en botón no, para evitar que se elimine
    cy.contains("No").click();
  });

   it("Añadir una nota nueva al swimlane uno", () => {
    //pinchar en el botón añadir del swimlane 1
    cy.get("#list0 > mat-card > mat-card-content > button").click();
    //verificar que se ve el formulario.
    cy.get("mat-dialog-container").should("be.visible");
    //rellenar los campos
    cy.get("textarea[type=text]").type(
      "Crear una función que permita añadir ficheros a la nota"
    );
    //click en el botón guardar
    cy.contains("Guardar").click();
    //comprobar que se ha escrito
    cy.contains("añadir").should("be.visible");
  });
 
/* // mover nota de un swimlane a otro
it("Mover una nota de un swimlane a otro", () => {
  cy.get('#3')
    .drag("#lane_1", {
      source: { x: 100, y: 100 }, // applies to the element being dragged
      target: { position: "left" }, // applies to the drop target
      force: true, // applied to both the source and target element
    });
}); */
   it("Pinchar en el botón adjuntar", () => {
    cy.clock();
    //pinchar en el botón adjuntar a la nota con index 0
    cy.get(
      '#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card > .mat-card-content > .file > .space-between > .mat-focus-indicator > .mat-button-wrapper > .mat-icon'
    ).click()
    cy.tick(1000)
  }); 
});
