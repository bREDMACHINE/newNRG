package get.a.big.head.newNRG.factories;

public class FactoryMapper {

    public static Factory toFactory(FactoryDto factoryDto) {
        Factory factory = new Factory();
        factory.setFactoryName(factoryDto.getFactoryName());
        return factory;
    }

    public static FactoryDto toFactoryDto(Factory factory) {
        return FactoryDto.builder()
                .factoryId(factory.getFactoryId())
                .factoryName(factory.getFactoryName())
                .build();
    }
}
