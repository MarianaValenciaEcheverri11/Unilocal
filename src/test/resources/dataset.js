
db = connect('mongodb://root:example@localhost:27017/proyecto-test?authSource=admin');

db.clientes.insertMany([
    {
        _id: "C2",
        nombre: 'Juan',
        email: ''
    },
])
