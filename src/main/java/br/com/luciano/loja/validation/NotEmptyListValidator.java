package br.com.luciano.loja.validation;

import br.com.luciano.loja.dto.ItemPedidoDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List<ItemPedidoDTO>> {

    @Override
    public boolean isValid(List<ItemPedidoDTO> list, ConstraintValidatorContext context) {
        return list != null && !list.isEmpty();
    }
}
