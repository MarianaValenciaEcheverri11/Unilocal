
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
        historicoCategoriasBuscadas: ["RESTAURANTE", "BAR"],
        nickName: "Resalec",
        nombre: "Andres",
    },
    {
        ciudad: "Pereira",
        codigoFavorito: [],
        email: "andreciito37@gmail.com",
        foto: "http://res.cloudinary.com/duykwcgkw/image/upload/v1713369817/unilocal/wk8cvjxkwkn68xnydidj.jpg",
        rol: "MODERADOR",
        contrasena: "$2a$10$vwiSf/XinZWFFGpR6POwv.fg.EVur5ykQqr8o.QBX1Y9/kdKKT2VO",
        estado: "ACTIVA",
        historicoCategoriasBuscadas: ["RESTAURANTE", "BAR"],
        nickName: "Resalec2",
        nombre: "Andres2",
    }
])
db.save()

const cliente = db.clientes.findOne({ rol: "CLIENTE" });

const clienteModerador = db.clientes.findOne({ rol: "MODERADOR" });

db.establecimientos.insertMany([
    {
        imagenes: [
        ],
        descripcion: "Un lugar para disfrutar de la mejor comida",
        nombre: "Restaurante",
        telefonos: [
        ],
        ubicacion: {
            longitud: 222.0,
            latitud: 222.0
        },
        horarios: [
            {
                horaApertura: "5",
                horaCierre: "6",
                dia: "LUNES"
            }
        ],
        codigoUsuario: clienteModerador._id,
        categoria: "RESTAURANTE"
    }
])

db.save()

const establecimiento = db.establecimientos.findOne({});

db.comentarios.insertMany([
    {
        fecha: '2021-06-01',
        valoracion: 5,
        codigoCliente: cliente._id,
        codigoEstablecimiento: establecimiento._id,
        resenia: "Excelente servicio",
        respuesta: ""
    }
])

db.save()

db.revisiones.insertMany([
    {
        codigoEstablecimiento: "string",
        descripcion: "Por favor corregir la ubicacion",
        estado: "APROBADA",
        fecha: "2021-06-01",
        codigoModerador: clienteModerador._id
    }
])
db.save()