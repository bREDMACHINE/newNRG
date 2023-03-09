package get.a.big.head.newNRG.factories;

import java.util.List;

public interface FactoryService {

    FactoryDto addFactory(FactoryDto factoryDto);
    void deleteFactory(Long id);
    FactoryDto getFactory(Long id);
    List<FactoryDto> findAllFactories();
}
