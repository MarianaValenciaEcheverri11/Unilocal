//crear la conexion con el servidor de mongo db y la base de datos
//spring.data.mongodb.uri=mongodb://root:example@localhost:27017/proyecto?authSource=admin
conn = new Mongo();
db = conn.getDB("tienda");

db.clientes.insertMany([
    {
        nombre: 'Juan',
        apellido: 'Perez',
        email: ''
    },
    {
        nombre: 'Maria',
        apellido: 'Gomez',
        email: ''
    },
    {
        nombre: 'Carlos',
        apellido: 'Lopez',
        email: ''
    },
    {
        nombre: 'Luis',
        apellido: 'Gonzalez',
        email: ''
    },
    {
        nombre: 'Ana',
        apellido: 'Diaz',
        email: ''
    }
])
