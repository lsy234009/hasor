/**********************************************************\
 |                                                          |
 |                          hprose                          |
 |                                                          |
 | Official WebSite: http://www.hprose.com/                 |
 |                   http://www.hprose.org/                 |
 |                                                          |
 \**********************************************************/
/**********************************************************\
 *                                                        *
 * DateTimeArraySerializer.java                           *
 *                                                        *
 * DateTime array serializer class for Java.              *
 *                                                        *
 * LastModified: Aug 6, 2016                              *
 * Author: Ma Bingyao <andot@hprose.com>                  *
 *                                                        *
 \**********************************************************/
package net.hasor.rsf.libs.com.hprose.io.serialize;
import net.hasor.rsf.libs.com.hprose.io.HproseTags;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public final class DateTimeArraySerializer extends ReferenceSerializer<Date[]> {
    public final static DateTimeArraySerializer instance = new DateTimeArraySerializer();

    @Override
    public final void serialize(Writer writer, Date[] array) throws IOException {
        super.serialize(writer, array);
        OutputStream stream = writer.stream;
        stream.write(HproseTags.TagList);
        int length = array.length;
        if (length > 0) {
            ValueWriter.writeInt(stream, length);
        }
        stream.write(HproseTags.TagOpenbrace);
        for (int i = 0; i < length; ++i) {
            Date e = array[i];
            if (e == null) {
                stream.write(HproseTags.TagNull);
            } else {
                DateTimeSerializer.instance.write(writer, e);
            }
        }
        stream.write(HproseTags.TagClosebrace);
    }
}
