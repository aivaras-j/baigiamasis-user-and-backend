package baigiamasis.UsersOfSportOrganizer.SportType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SportTypes {

    @NonNull
    public List<SportType> sportTypes;

}
