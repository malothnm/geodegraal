package in.nmaloth.geodegraal.model;

import lombok.*;
import org.apache.geode.DataSerializable;
import org.apache.geode.pdx.PdxSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo implements DataSerializable {

    private String id;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", badgeId=" + badgeId +
                '}';
    }

    private String name;
    private Integer badgeId;


    @Override
    public void toData(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeUTF(name);
        dataOutput.writeInt(badgeId);

    }

    @Override
    public void fromData(DataInput dataInput) throws IOException, ClassNotFoundException {
        id = dataInput.readUTF();
        name = dataInput.readUTF();
        badgeId = dataInput.readInt();
    }
}
