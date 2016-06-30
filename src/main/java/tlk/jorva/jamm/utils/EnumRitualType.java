package tlk.jorva.jamm.utils;

public enum EnumRitualType {
	BASIC, FUSION, WEAPONRY, SOUL;
	
	public static EnumRitualType[] getTypes(){
		return new EnumRitualType[]{BASIC, FUSION, WEAPONRY, SOUL};
	}
}
