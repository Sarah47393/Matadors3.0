<?php

namespace App\Entity;

use App\Repository\DonsRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * @ORM\Entity(repositoryClass=DonsRepository::class)
 */
class Dons
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="vérifier votre num_carte")
     */
    private $num_carte;

    /**
     * @ORM\Column(type="decimal", precision=10, scale=0) 
    *@Assert\NotBlank(message="vérifier votre montant")
    */
    private $montant;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="dons", cascade={"persist", "remove"})
     */
    private $user;

    
    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNumCarte(): ?int
    {
        return $this->num_carte;
    }

    public function setNumCarte(int $num_carte): self
    {
        $this->num_carte = $num_carte;

        return $this;
    }

    public function getMontant(): ?string
    {
        return $this->montant;
    }

    public function setMontant(string $montant): self
    {
        $this->montant = $montant;

        return $this;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }

   
}
