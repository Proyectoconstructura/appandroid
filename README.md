Para iniciar sesion, estan los usuarios siguientes 

    usuario     |   contaseña   |    rol
    Jefe de obra    |               |   jefe de obra
    Jefe de compra  |               |   jefe de compras
    Almacenita  |               |   almacenita
    Residente   |               |   residente

    Para que la api arroje datos en texto plano
    http://167.172.154.123:8082/Almacen
    http://167.172.154.123:8082/Entrada
    http://167.172.154.123:8082/Listainsumo
    http://167.172.154.123:8082/Requisicion
----------------------------------------------------------

    MODELO
    public class Almacenes
    {
        [Key]
        public int id_almacen { set; get; }
        public string decripcion { set; get; }
        public string direccion { set; get; }
        [ForeignKey("id_almacen")]
        [JsonIgnore]
        public ICollection<Entradas> entrada { get; set; }
    }
    ----------------------------------------------------------------------
    public class Entradas
    {
        [Key]
        public int id_entrada { set; get; }
        public int id_almacen { set; get; }
        public Almacenes almacenes { set; get; }
        public int id_insumo { set; get; }
        public Insumos insumos{ set; get; }
        public int id_proveedor { set; get; }
        public Proveedores proveedores { set; get; }
        public int id_compra { set; get; }
        public Requisicion_Compras compras { set; get; }
        public DateTime fecha_entrada { set; get; }
        public float cantidad { set; get; }
        public float importe { set; get; }
        public float precio_unitario{ set; get; }
        [ForeignKey("id_entrada")]
        [JsonIgnore]
        public ICollection<Inventario> inventario { get; set; }
    }
    ----------------------------------------------------------------------
    public class Estructuras
    {
        [Key]
        public int id_estructura {set; get;}
        public string descripcion {set; get;}
        public int id_insumo {set; get;}
        public Insumos insumos { set; get; }
        public float cantidad {set; get;}
        [ForeignKey("id_estructura")]
        [JsonIgnore]
        public ICollection<Modulos> modulo { get; set; } 
    }
    ----------------------------------------------------------------------
    public class Insumos
    {
        [Key]
        public int id_insumo { get; set; }
        public string clave { get; set; }
        public string descripcion { get; set; }
        public int id_partida { get; set; }
        public Partidas partida { get; set; }
        public string unidad_medida { get; set; }
        [ForeignKey("id_insumo")]
        [JsonIgnore]
        public ICollection<listainsumo> lisaInsumo { get; set; }
        [ForeignKey("id_insumo")]
        [JsonIgnore]
        public ICollection<Requisicion_Compras> requisicioncompra { get; set; }
        [ForeignKey("id_insumo")]
        [JsonIgnore]
        public ICollection<Requisiciones_Insumo> requisicioninsumos { get; set; }
        [ForeignKey("id_insumo")]
        [JsonIgnore]
        public ICollection<Inventario> inventario { get; set; }
        [ForeignKey("id_insumo")]
        [JsonIgnore]
        public ICollection<Estructuras> estructuras { get; set; }
        [ForeignKey("id_insumo")]
        [JsonIgnore]
        public ICollection<Entradas> entrada { get; set; }
    }
    ----------------------------------------------------------------------
     public class Inventario
    {
        [Key]
        public int id_inventario { set; get; }
        public int id_insumo { set; get; }
        public Insumos insumos { set; get; }
        public int id_entrada { set; get; }
        public Entradas entradas { set; get; }
        public float cantidad { set; get; }
    }
    ----------------------------------------------------------------------
     public class listainsumo
    {
        [Key]
        public int id_listainsumo { get; set; }
        public int id_insumo { get; set; }
        public Insumos insumos{ get; set; }
        public string proyecto { get; set; }
        public float cantidad { get; set; }
        public bool status { get; set; }
       
    }
    ----------------------------------------------------------------------
     public class Modulos
    {
        [Key]
        public int modulo_id { set; get; }
        public string descripcion { set; get; }
        public int id_estructura { set; get; }
        public Estructuras estructuras { set; get; }
        public float insumo_modulo { set; get; } 
    }
    ----------------------------------------------------------------------
     public class Partidas
    {
        [Key]
        public int id_partida { set; get; }
        public string descripcion { set; get; }
        [ForeignKey("id_partida")]
        [JsonIgnore]
        public ICollection<Insumos> insumos { get; set; }
    } 
    ----------------------------------------------------------------------
     public class Proveedores
    {
        [Key]
        public int id_proveedor { set; get; }
        public string nombre { set; get; }
        public string rfc { set; get; }
        public string direccion { set; get; }
        [ForeignKey("id_proveedor")]
        [JsonIgnore]
        public ICollection<Entradas> entrada { get; set; }
    }
    ----------------------------------------------------------------------
     public class Requisicion_Compras
    {
        [Key]
        public int  id_compra { set; get; }
        public int id_insumo { set; get; }
        public Insumos insumos { set; get; }
        public DateTime fecha { set; get; } 
        public float cantidad { set; get; }
        public string comprador { set; get; }
        public int numero { set; get; }
        public string solicitante{ set; get; }
        public string revisor  { set; get; }
        public string autorizante{ set; get; }
        public string observaciones { set; get; }
        public string centro_costo { set; get; }
        public string prioridad { set; get; }
        public string unidad{ set; get; }
        [ForeignKey("id_compra")]
        [JsonIgnore]
        public ICollection<Entradas> entrada { set; get; }
    }
    ----------------------------------------------------------------------
     public class Requisiciones_Insumo
    {
        [Key]
        public int  id_requisicion { set; get; }
        public int id_insumo { set; get; }
        public Insumos insumos { set; get; }
        public DateTime fecha { set; get; } 
        public float cantidad { set; get; }
        public int numero { set; get; }
        public string solicitante{ set; get; }
        public string revisor  { set; get; }
        public string autorizante{ set; get; }
        public string observaciones { set; get; }   
        public string centro_costo { set; get; }
        public string prioridad { set; get; }
        public string unidad{ set; get; }
    }
    ----------------------------------------------------------------------
     public class Roles
    {
        [Key]
        public int id_rol { get; set;  }
        public string descripcion { set; get; }
        [ForeignKey("id_rol")]
        [JsonIgnore]
        public ICollection<Usuarios> Usuarios { get; set; }
    }
    ----------------------------------------------------------------------
     public class Usuarios
    {
        [Key]
        public int id_usuario { set; get; }
        public string nombre { set; get; }
        public string apellido { set; get; }
        public int id_rol { set; get; }
        public Roles roles { set; get; }
        public DateTime ultima_entrada { set; get; }
    } 
    ----------------------------------------------------------------------

