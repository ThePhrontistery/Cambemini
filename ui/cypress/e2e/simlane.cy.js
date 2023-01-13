describe("Test relacionados con Swimlanes", () => {
  it("verificar que hay al menos una swimlane", () => {
    //inicio sesión
    cy.login();
    //entro a un Kanban
    cy.kanban();
    //verificar que hay una swimlane
    cy.get("#list0 > .kanbas").should("be.visible");
  });

  it("Añadir una swimlane", () => {
    cy.login();
    cy.kanban();
    //hacer click en el botón azul de añadir un swimlane
    cy.get(".addButton > .mat-focus-indicator").click();
    //verificar que sale el dialogo
    cy.get(".mat-dialog-container").should("be.visible");
    //escribir un título
    cy.get("#mat-input-3").type("Por hacer");
    //hacer click en el botón de guardar
    cy.contains("Guardar").click();
  });

  //verificar que salen los mensajes de error a la hora de añadir un swimlane
  it("Mensajes de error", () => {
    cy.login();
    cy.kanban();
    cy.get(".addButton > .mat-focus-indicator").click();
    cy.get("mat-dialog-container").should("be.visible");
    //pinchar en el título
    cy.get("#mat-input-3").click();
    //hacer click en el botón guardar sin escribir
    cy.contains("Guardar").click();
    //verifico si se muestran los mensajes de error correctos
    cy.get("mat-error").should("have.length", 1);
    cy.get("mat-error").should("have.text", "El titulo no puede estar vacío");
    //hago click en el botón cerrar
    cy.contains("Cerrar").click();
  });

  //Editar un swimlane y guardar
  it("Editar un swimlane", () => {
    cy.login();
    cy.kanban();
    //hacer click en el botón editar
    cy.get(
      "#list0 > .kanbas > .mat-card-header.w-100 > .space-between > div > :nth-child(1) > .mat-button-wrapper > .mat-icon"
    ).click();
    //verificar que se abre el dialogo
    cy.get("mat-dialog-container").should("be.visible");
    //borrar título anterior
    cy.get("#mat-input-3").clear();
    //escribir un título
    cy.get("#mat-input-3").type("En progreso");
    //darle a guardar
    cy.contains("Guardar").click();
  });

  //dar a editar y pinchar en cancelar
  it("Editar y cancelar", () => {
    cy.login();
    cy.kanban();
    cy.get(
      "#list0 > .kanbas > .mat-card-header.w-100 > .space-between > div > :nth-child(1) > .mat-button-wrapper > .mat-icon"
    ).click();
    cy.get("mat-dialog-container").should("be.visible");
    cy.contains("Cerrar").click();
  });

  //borrar un swimlane
  it("Borrar un swimlane", () => {
    cy.login();
    cy.kanban();
    //pinchar en botón eliminar
    cy.get(
      "#list0 > .kanbas > .mat-card-header.w-100 > .space-between > div > :nth-child(2) > .mat-button-wrapper > .mat-icon"
    ).click();
    //verificar que sale el diálogo de confirmación
    cy.get("mat-dialog-container").should("be.visible");
    //pinchar en sí
    cy.contains("Sí").click();
  });

  //dar a borrar, pero cancelar la petición
  it("Borrar y cancelar", () => {
    cy.login();
    cy.kanban();
    //pinchar en botón eliminar
    cy.get(
      "#list0 > .kanbas > .mat-card-header.w-100 > .space-between > div > :nth-child(2) > .mat-button-wrapper > .mat-icon"
    ).click();
    //verificar que sale el diálogo de confirmación
    cy.get("mat-dialog-container").should("be.visible");
    //pinchar en no
    cy.contains("No").click();
  });

  //verificar que hace drag and drop
  it("Mover primer swimlane a la segunda posición", () => {
    cy.login();
    cy.kanban();

 /*    const dataTransfer = new DataTransfer();

    cy.get('#list0 > .kanbas').trigger("drag", {
      dataTransfer,
    })

    cy.get('#list2 > .mat-card').trigger("drop", {
      dataTransfer,
    })
 */
    cy.get('#list0 > .kanbas').drag('#list1 > .kanbas')

  });
});
