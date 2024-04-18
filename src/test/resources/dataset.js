
db = connect('mongodb://root:example@localhost:27017/proyecto-test?authSource=admin');

db.clientes.insertMany([
    {
        ciudad: "Pereira",
        codigoFavorito: [],
        email: "andreciito38@gmail.com",
        foto: "http://res.cloudinary.com/duykwcgkw/image/upload/v1713369817/unilocal/wk8cvjxkwkn68xnydidj.jpg",
        rol: "CLIENTE",
        contrasena: "$2a$10$vwiSf/XinZWFFGpR6POwv.fg.EVur5ykQqr8o.QBX1Y9/kdKKT2VO",
        estado: "ACTIVA",
        historicoCategoriasBuscadas: ["Panaderia", "Mecanica"],
        nickName: "Resalec",
        nombre: "Andres",
    }
])
