public class Mapa {
	
	private String chave;
	private Object dado;
	
	public Mapa() {}
	
	public Mapa(String chave, Object dado) {
		this.chave = chave;
		this.dado = dado;
	}
	
	public String getChave(){
		return chave;
	}
	
	public void setChave(String chave){
		this.chave = chave;
	}
	
	public Object getDado() {
		return dado;
	}
	
	public void setDado(Object dado) {
		this.dado = dado;
	}
}