//Hacer login
describe('Login form', () => {
 beforeEach(()=>{
cy.visit('/login');
 });
//define una prueba individual que verifica si el formulario de inicio de sesión se muestra correctamente en la página.
  it('Comprueba que se muestra el formulario de inicio de sesión', () => {
    //busca  un elemento con la clase "login-form" y verifica si es visible
    cy.get('.login-form').should('be.visible');
    //las siguientes líneas de código realizan comprobaciones para verificar si otros elementos del formulario tienen el texto, los atributos y los placeholders correctos.
    cy.get('.title').should('have.text', 'Canbemini');
    cy.get('input[type=email]').should('have.attr', 'name', 'email');
    cy.get('input[type=email]').should('have.attr', 'required');
    cy.get('input[type=email]').should('have.attr', 'placeholder', 'Email');
    cy.get('input[type=password]').should('have.attr', 'name', 'password');
    cy.get('input[type=password]').should('have.attr', 'required');
    cy.get('input[type=password]').should('have.attr', 'placeholder', 'Password');
    cy.get('button[type=submit]').should('have.text', 'Login');
  });
  
//defino otra prueba individual que verifica si se muestran mensajes de error cuando el usuario envía el formulario sin completar los campos
  it('debería mostrar mensajes de error cuando el formulario se envía con campos vacíos', () => {
    //esta función es la que realiza click en el botón de envío de formulario
    cy.get('button[type=submit]').click();
    //verifico si se muestran los mensajes de error correctos
    cy.get('mat-error').should('have.length', 2);
    cy.get('mat-error').first().should('have.text', 'El correo no puede estar vacío');
    cy.get('mat-error').last().should('have.text', 'La contraseña no puede estar vacía');
  });

  //defino una prueba individual que me muestre la contraseña
  it('debería mostrar la constraseña al pulsar en el botón', ()=>{
    //escribe el email
    cy.get("input[name=email]").type("cesar@email.com");
    //escribe la contraseña
    cy.get("input[name=password]").type("123");
    //click en el ojo
    cy.get('.mat-form-field-suffix > .mat-icon').click()
    //espero tres segundos, porque no se veía que mostrase la contraseña
    cy.wait(3000)
  })

  /*defino una prueba individual que haga click en el checkbox de remeber me, lo correcto sería hacer .check
  pero en este caso no me dejaba*/
  it('Recuérdame', ()=>{
    cy.get('.mat-checkbox-inner-container').click()
    cy.wait(1000)
  });

  //defino una prueba que me compruebe que cierra sesión correctamente
  it('Cerrar sesión', ()=>{
    cy.login()
    //compruebo que he entrado a la página de "/kanbans"
    cy.url().should('contain', '/kanbans');
    //espero un segundo
    cy.wait(1000)
    cy.get('.space-between > .mat-focus-indicator > .mat-button-wrapper > .mat-icon').click()
  });

  //pruebo que inicia sesión con varios usuarios
  it('inicio de sesión con varios usuarios', ()=>{
    /*Itera sobre el array de usuarios del json,en cada iteración se está accediendo 
    a los valores de email y password del objeto usuario para ingresar en los campos correspondientes
    */
    cy.fixture('usuarios').then(usuarios => {
      usuarios.forEach(usuario => {
          cy.visit('/login');
          cy.get('input[name=email]').type(usuario.email);
          cy.get('input[name=password]').type(usuario.password);
          cy.get('button[type=submit]').click();
          //espero 3 segundos
          cy.wait(3000);
          //verifico si ha entrado en la página que corresponde
          cy.url().should('contain', '/kanbans');
      });
  });
  

  });
 
});
 
