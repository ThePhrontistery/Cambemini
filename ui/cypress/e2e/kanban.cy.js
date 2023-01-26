describe("test relacionados con kanbans", () => {
  beforeEach(()=>{
    cy.login();
  });
  //defino una prueba individual de creación de un kanban
  it("Debe añadir un kanban a un usuario", () => {
    //verifico que el botón es visible
    cy.get("button[mat-fab][type=addButton]").should("be.visible");

    cy.get("button[mat-fab][type=addButton]").click(); // Hacer clic en el botón Añadir

    // Verificar que se muestra el formulario de nuevo Kanban
    cy.get("form").should("be.visible");

    // Rellenar el formulario de nuevo Kanban
    cy.get("input[name=title]").type("Mi nuevo Kanban")
    cy.get("textarea[name=description]").type(
      "Una descripción para mi nuevo Kanban"
    )

    // esto debería hacer click en el botón
    cy.get("body").click()

    //verifica que la url actual del navegador contenga "/kanbans", si es así se considera una prueba exitosa, sino, falla
    cy.url().should("contain", "/kanbans")
  });
  //verifica si se muestran mensajes de error cuando el usuario envía el formulario sin completar los campos
  it("debería mostrar mensajes de error cuando el formulario se envía con campos vacíos", () => {
    cy.get("button[mat-fab][type=addButton]").click()
    cy.get("form").should("be.visible");
    //hacer click en title
    cy.get("input[name=title]").click()
    //hacer click en description 
    cy.get("textarea[name=description]").click()
    //enviar datos vacíos hacer click en el botón enviar del formulario, sin rellenar datos
    cy.get("body").click()
    //verifico si se muestran los mensajes de error correctos
    cy.get("mat-error").should("have.length", 2);
    cy.get("mat-error")
      .first()
      .should("have.text", "El título no puede estar vacío");
    cy.get("mat-error")
      .last()
      .should("have.text", "La descripcion no puede estar vacía");
  });
  //comprobar que al menos haya un kanban
  it("comprobar que exista un kanban", () => {
    cy.get(".mat-card").should("be.visible");
  });

  //hacer click en el primer kanban
  it("Hacer click en el primer Kanban", () => {
    cy.get(".mat-card").click();
    //verifica que la url actual del navegador sea la que quiero
    cy.url().should("contain", "/kanbans/1");
    //verificar que hay un título
    cy.get(".header_menu > .ml").should("be.visible");
  });

  //entrar en un kanban y salir
  it("Entrar en un kanban y salir a la página principal", () => {
    //hacer click en un kanban
    cy.get(".mat-card").click();
    //verificar la url
    cy.url().should("contains", "/kanbans/1");
    //pinchar en el icono de la casa que es el de volver a la lista de kanbans
    cy.get(".header_title > .mat-icon").click();
  });

  //Cambiar a un editor y hacerle owner, por ejemplo, y guardar
  it("Cambio de rol y guardar", () => {
    //pinchar en el kanban
    cy.get(".mat-card").click();
    //pinchar en este caso el usuario dos
    cy.get(".justify-content-center > :nth-child(2)").click();
    //verificar que sale el mat dialog
    cy.get("mat-dialog-container").should("be.visible");
    //elegir del selector
    cy.get("mat-select").click();
    cy.get("mat-option").contains("Owner").click();
    cy.contains("Guardar").click();
  });

  //cambiar de rol, pero dar al botón salir
  it("Cambio de rol y dar al botón salir", () => {
    cy.get(".mat-card").click();
    cy.get(".justify-content-center > :nth-child(2)").click();
    //verificar que sale el mat dialog
    cy.get("mat-dialog-container").should("be.visible");
    //elegir del selector
    cy.get("mat-select").click();
    cy.get("mat-option").contains("Owner").click();
    cy.contains("Cerrar").click();
  });

  //borrar un kanban
  it("Eliminar un Kanban", () => {
    //pinchar en el botón eliminar
    cy.get(".mat-warn > .mat-button-wrapper > .mat-icon").click();
    //verificar que se muestra el dialogo de control
    cy.get("#mat-dialog-0").should("be.visible");
    //dar al botón de aceptar en el dialogo
    cy.get(".mat-flat-button").click();
  });

  //borrar un Kanban, pero dar al botón de no eliminar
  it("Eliminar un Kanban, pero no aceptar cambios", () => {
    //pinmchar en el botón eliminar
    cy.get(".mat-warn > .mat-button-wrapper > .mat-icon").click();
    //verificar que se muestra el dialogo
    cy.get("#mat-dialog-0").should("be.visible");
    //dar al botón NO
    cy.get(".mat-stroked-button").click();
  });

  //generar enlace de "Compartir"
  it("Generar enlace", () => {
    //pinchar en shared
    cy.get(".mat-tooltip-trigger").click();
    //verificar que se muestra snack bar
    cy.get("snack-bar-container").should("be.visible");
    //Pinchar en el OK del snack bar
    cy.contains("OK!").click();
  });

  //Editar un kanban y aceptar
  it("Editar Kanban", () => {
    //pinchar en edit
    cy.get(".mat-card-actions > .mat-primary").click();
    //Verificar que se muestra el dialógo
    cy.get("#mat-dialog-0").should("be.visible");
    //limpiar el campo título
    cy.get("#mat-input-2").clear();
    //rellenar el campo con el nuevo título
    cy.get("#mat-input-2").type("Proyecto Cambemini");
    //limpiar el campo descripción
    cy.get("#mat-input-3").clear();
    //rellenar el campo descripción
    cy.get("#mat-input-3").type(
      "Canbemini es una herramienta fácil de usar para crear Kanbans. Con una interfaz de usuario simple, puede instalar y configurar rápidamente sus Kanbans para diferentes flujos de procesos"
    );
    //clicar en el botón aceptar
    cy.get(".mat-flat-button").click();
  });

  //Editar un kanban, pero darle al botón cancelar
  it("Editar y cancelar", () => {
    //pinchar en edit
    cy.get(".mat-card-actions > .mat-primary").click();
    //Verificar que se muestra el dialógo
    cy.get("#mat-dialog-0").should("be.visible");
    //pinchar en cancelar
    cy.get(".mat-stroked-button").click();
  });
});
