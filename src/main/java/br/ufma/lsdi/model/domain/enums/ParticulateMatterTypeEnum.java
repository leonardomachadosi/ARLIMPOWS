package br.ufma.lsdi.model.domain.enums;

public enum ParticulateMatterTypeEnum {
    PM10(1L),
    PM25(2L);

    public Long value;

    ParticulateMatterTypeEnum(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
