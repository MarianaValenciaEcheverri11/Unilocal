
db = connect('mongodb://root:example@localhost:27017/proyecto-test?authSource=admin');

db.clientes.insertMany([
    {
        _id: "C1",
        ciudad: "Pereira",
        codigoFavorito: ["12", "13"],
        email: "andreciito38@gmail.com",
        foto: "http://res.cloudinary.com/duykwcgkw/image/upload/v1713369817/unilocal/wk8cvjxkwkn68xnydidj.jpg",
        rol: "USUARIO",
        contrasena: "$2a$10$vwiSf/XinZWFFGpR6POwv.fg.EVur5ykQqr8o.QBX1Y9/kdKKT2VO",
        estado: "ACTIVA",
        historicoCategoriasBuscadas: ["Panaderia", "Mecanica"],
        nickName: "Resalec",
        nombre: "Andres",
    }
])

db.comentarios.insertMany([
    {
        _id: "com1",
        fecha: "12/12/12",
        valoracion: 5,
        codigoCliente: "C1",
        codigoEstablecimiento: "E1",
        resenia: "La mejor panaderia de la ciudad",
        respuesta: ""
    }
])

db.establecimientos.insertMany([
    {
        _id: 'E1',
        imagenes: [
            'imagen1',
            'imagen2',
            'imagen3'
        ],
        descripcion: 'Aca se vende pan de calidad',
        nombre: 'Pan de la abuela',
        telefonos: [
            '123123',
            '1123123'
        ],
        ubicacion: {
            longitud: 12414.2,
            latitud: 12414.2
        },
        horarios: [
            {
                horaApertura: '8:00',
                horaCierre: '18:00',
                dia: 'LUNES'
            },
            {
                horaApertura: '8:00',
                horaCierre: '18:00',
                dia: 'MARTES'
            }
        ],
        codigoUsuario: 'C1',
        categoria: "Panaderia"
    }
])

db.revisiones.insertMany([
    {
        codigo: '32' ,
        codigoPublicacion: '23',
        descripcion: 'falta mejorar el servicio',
        estado:'rechazada',
        fecha: '12/12/12',
        codigoModerador: 'C1',
    },
    {
        codigo: '33' ,
        codigoPublicacion: '23',
        descripcion: 'falta mejorar el servicio',
        estado:'rechazada',
        fecha: '12/12/12',
        codigoModerador: 'C1',
    },
    {
        codigo: '34' ,
        codigoPublicacion: '23',
        descripcion: 'falta mejorar el servicio',
        estado:'rechazada',
        fecha: '12/12/12',
        codigoModerador: 'C1',
    }
])
