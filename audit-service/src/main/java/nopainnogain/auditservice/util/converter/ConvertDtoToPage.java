package nopainnogain.auditservice.util.converter;

import nopainnogain.auditservice.core.dto.ConvertDto;
import nopainnogain.auditservice.core.dto.PageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConvertDtoToPage implements Converter<ConvertDto, PageDto> {
    @Override
    public PageDto convert(ConvertDto source) {
        return new PageDto<>(source.allPage().getNumber(),
                             source.allPage().getSize(),
                             source.allPage().getTotalPages(),
                             source.allPage().getTotalElements(),
                             source.allPage().isFirst(),
                             source.allPage().getNumberOfElements(),
                             source.allPage().isLast(),
                             source.content());
    }
}
