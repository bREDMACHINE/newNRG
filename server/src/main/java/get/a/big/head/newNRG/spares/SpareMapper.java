package get.a.big.head.newNRG.spares;

public class SpareMapper {

    public static Spare toSpare(SpareDto spareDto) {
        Spare spare = new Spare();
        spare.setSpareName(spareDto.getSpareName());
        spare.setSpareDescription(spareDto.getSpareDescription());
        spare.setSpareCode(spareDto.getSpareCode());
        return spare;
    }

    public static SpareDto toSpareDto(Spare spare) {
        return SpareDto.builder()
                .spareId(spare.getSpareId())
                .spareName(spare.getSpareName())
                .spareDescription(spare.getSpareDescription())
                .spareCode(spare.getSpareCode())
                .build();
    }
}
