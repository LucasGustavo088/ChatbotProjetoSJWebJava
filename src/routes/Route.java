package routes;

public class Route {
	private String url;
	private String Controller;
	private String funcao;
	private String name;
	
	public Route(String url, String controller, String funcao, String name) {
		super();
		this.url = url;
		this.name = name;
		Controller = controller;
		this.funcao = funcao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getController() {
		return Controller;
	}

	public void setController(String controller) {
		Controller = controller;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	
	
}
