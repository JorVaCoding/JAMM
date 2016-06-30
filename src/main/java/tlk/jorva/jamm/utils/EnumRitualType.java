package tlk.jorva.jamm.utils;

public enum EnumRitualType {
	BASIC("BASIC"), FUSION("FUSION"), WEAPONRY("WEAPONRY"), SOUL("SOUL");
	
	private EnumRitualType(String name) {
	}
	
	public static EnumRitualType[] getTypes(){
		return new EnumRitualType[]{BASIC, FUSION, WEAPONRY, SOUL};
	}
}
