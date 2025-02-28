/**
* Los servicios que debe ofrecer un Recipiente .
*/
public interface ServiciosRecipiente {

	/**
	* Calcula y devuelve la cantidad maxima de lıquido que cabe en
	* el recipiente.
	*
	* @return La capacidad total.
	*/
	public double capacidad();

	/**
	* Calcula y devuelve la diferencia entre la capacidad total y
	* la cantidad actual del recipiente.
	*
	* @return La capacidad restante.
	*/
	public double capacidadRestante();

	/**
	* Indica si el recipiente esta vacıo, es decir, la cantidad
	* de lıquido que contiene es cero.
	*
	* @return true si esta vacıo, false en otro caso.
	*/
	public boolean estaVacio();

	/**
	* Indica si el recipiente esta lleno, es decir, la cantidad
	* de lıquido que contiene es igual a la de su capacidad.
	*
	* @return true si esta lleno, false en otro caso.
	*/
	public boolean estaLleno();

	/**
	* Agrega una nueva cantidad de lıquido al recipiente, respetando
	* la capacidad maxima del mismo. En caso de que no quepa todo
	* el lıquido, debera devolver la cantidad excedente.
	* Por ejemplo, si un recipiente tiene cantidad actual 15 y
	* capacidad total 20, al rellenarlo con 10 deberıa actualizar
	* la cantidad actual a 20 y devolver un excedente de 5.
	*
	* @param cantidad La cantidad de lıquido a agregar.
	* @return La cantidad excedente. Puede ser cero si cabıa todo.
	*/
	public double rellena(double cantidad );

	/**
	* Resetea la cantidad actual a cero .
	*
	* @return La cantidad de lıquido que poseıa.
	*/
	public double vacia();
	/**
	* Vierte el contenido del recipiente en otro respetando su
	* capacidad maxima. En caso de haber excedente, se debe guardar
	* en el recipiente invocante.
	* Por ejemplo, si un recipiente A tiene cantidad actual 10 y
	* vertemos su contenido en un recipiente B con cantidad actual 3
	* y capacidad total 5, entonces debe dar como resultado que el
	* recipiente A quede con cantidad actual 8 y el recipiente B con
	* cantidad actual 5.
	*
	* @param otro El recipiente donde se vertira el lıquido actual.
	*/
	public void vierte(Recipiente otro );

	/**
	* Indica si el recipiente tiene exactamente las mismas
	* dimensiones que otro. Solo revisamos las dimensiones, la
	* cantidad de lıquido puede variar.
	*
	* @param otro El recipiente con que se comparara.
	* @return true si tienen las mismas dimensiones, false en otro caso.
	*/
	public boolean mismasDimensiones(Recipiente otro );

	/**
	* Indica si el recipiente tiene exactamente la misma
	* capacidad total que otro. Solo revisamos la capacidad total,
	* las dimensiones pueden variar.
	*
	* @param otro El recipiente con que se comparara.
	* @return true si tienen la misma capacidad total, false en otro caso.
	*/
	public boolean mismaCapacidad(Recipiente otro );

	/**
	* Indica si el recipiente tiene mayor cantidad actual que otro.
	*
	* @param otro El recipiente con que se comparara.
	* @return true si tiene mayor cantidad, false en otro caso.
	*/
	public boolean contieneMas(Recipiente otro );

	/**
	* Indica si el recipiente tiene mayor capacidad restante que
	* otro.
	*
	* @param otro El recipiente con que se comparara.
	* @return true si tiene mayor capacidad restante, false en otro caso.
	*/
	public boolean cabeMas(Recipiente otro );

	/**
	* Crea un nuevo recipiente vacıo cuya capacidad total es igual
	* a la cantidad actual del recipiente invocante.
	* Por ejemplo, a partir de un recipiente A de altura h y radio
	* de su base r que esta lleno solo hasta la mitad, se podrıa
	* generar un recipiente vacıo B de altura h/2 y radio de su base r.
	*
	* @return Un nuevo recipiente vacıo con capacidad total igual a
	* la cantidad actual del recipiente invocante.
	*/
	public Recipiente creaContenedorJusto();

	/**
	* Devuelve una cadena que exhiba con buen formato las
	* dimensiones, capacidad y cantidad actual del recipiente.
	*
	* @return La cadena correspondiente al recipiente.
	*/
	public String muestra();
}