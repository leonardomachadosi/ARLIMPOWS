package br.ufma.lsdi.model.domain.enums;

public enum CapabilityEnum {
    CARBOMO(1L),
    OZONIO(2L),
    NOTROGENIO(3L);

    public Long value;

    CapabilityEnum(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
