BEGIN
	--SI HAN CAMBIADO ALGO QUE ME IMPORTA
	IF(OLD.idab<>NEW.idab)THEN
	--SI VENGO DE UN UPDATE EN CASCADA
		IF(PG_TRIGGER_DEPTH()>1) THEN
			RETURN NEW;
		ELSE
			IF NOT EXISTS(SELECT * FROM c WHERE idac = OLD.idab) THEN 
				RAISE NOTICE 'NO PUEDES ACTUALIZAR ESTE B YA QUE TIENE RESTRICCIÓN DE TOTAL EN A';
				RETURN NULL;
			ELSE
				RETURN NEW;
			END IF;
		END IF;
	ELSE
		RETURN NEW;
	END IF;
END;