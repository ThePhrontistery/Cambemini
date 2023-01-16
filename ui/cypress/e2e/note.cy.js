describe("test relacionados con las notas", () => {
  it("Verificar que al menos hay una nota en el primer swimlane", () => {
    cy.login();
    cy.kanban();
    cy.get(
      '#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card'
    ).should("be.visible");
  });

  it("Modificar la primera nota del primer swimlane", () => {
    cy.login();
    cy.kanban();
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
    cy.login();
    cy.kanban();
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
    cy.login();
    cy.kanban();
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
    cy.login();
    cy.kanban();
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

  //mover nota de un swimlane a otro
  it('Mover una nota de un swimlane a otro', ()=>{
    cy.login()
    cy.kanban()
     // selecciono el elemento que se va a mover
     cy.get('#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card').then(($el) => {
        // obtengo la posiión inicial del elemento
        const initialPosition = $el.offset();
        let dropzonePosition;
        // comienza el evento
        cy.wrap($el).trigger('mousedown', { which: 1 });
  
        // mueve el elemento a otra posición
        cy.get('#list1 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card').then(($dropzone) => {
          dropzonePosition = $dropzone.offset();
          cy.get('#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card').trigger('mousemove', {
            clientX: dropzonePosition.left,
            clientY: dropzonePosition.top
          });
        });
  
        // suelta el ratón para soltar el elemento
        cy.get('#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card').trigger('mouseup', { force: true });
  
      });
  });

  it("Pinchar en el botón adjuntar", () => {
    cy.login();
    cy.kanban();
    cy.clock();
    //pinchar en el botón adjuntar a la nota con index 0
    cy.get(
      '#list0 > .kanbas > .min-width > [ng-reflect-index="0"] > [ng-reflect-ng-class="[object Object]"] > .mat-card > .mat-card-content > .file > .space-between > .mat-focus-indicator > .mat-button-wrapper > .mat-icon'
    ).click()
    cy.tick(1000)
  });

});
